#!/usr/bin/perl

use strict;
use warnings;

use v5.14;

use NetAddr::IP qw();
use File::Slurp::Tiny qw(read_file read_lines);

my $devname = shift;

my %mapping = map { chomp; split } (<>);
my $status;

if ( $devname =~ m/^wlan/
    and ( read_file("/etc/wpa_supplicant.conf") !~ m/network=\{\s+ssid/ms or read_file("/sys/class/net/eth0/carrier") == 1 ) )
{
    $status = "NONET";
}
else
{
    $status = "DYN";
    system(qw,/usr/bin/disable-error failed-DAD,);
    if ( defined $mapping{"STATIC"} ) {
        my @addr;
        my $intf = read_file("/etc/network/interfaces");
        $intf =~ s/#.*//g;
        while ( $intf =~ m,(?:iface\s${devname}-static.*?address\s+(\d+\.\d+\.\d+\.\d+).*?)(?:^$|\z),gsmx ) { push @addr, $1 }

        if ( ( scalar @addr == 1 ) and NetAddr::IP->new(@addr) > NetAddr::IP->new("0.0.0.0") )
        {
            # redir STDOUT
            open( my $old_stdout, ">&", STDOUT );
            close(STDOUT);
            open( STDOUT, ">&", STDERR );

            # DAD check
            system( qw,/usr/sbin/arping -c 5 -D -I,, $devname, @addr ) == 0 and $status = "STATIC";
            $status eq "STATIC" or system(qw,/usr/bin/enable-error failed-DAD,);

            # restore STDOUT
            open( STDOUT, ">&", $old_stdout );
        }
    }
}

$status or die "Unknown status";
defined $mapping{$status} or die "'$status' not mapable";
say $mapping{$status};

1
