#!/bin/sh

homedir="/home/homepilot"
appdir=".homepilot"
datadir="$homedir/$appdir"
valid_file="validated"
current_datadir=""


function create_initial() {
        initial="${datadir}_1"
	mkdir -p ${initial}/z-way
        touch "$initial/$valid_file"
        ln -s $initial $datadir
}

function read_current() {
        current_datadir=`readlink $datadir`
}

function iterate() {
        read_current
        for i in `ls -c1a $homedir | grep ${appdir}_ | cut -d'_' -f2 | cut -d'/' -f1 | sort -nr`; do
                file="${homedir}/${appdir}_${i}"
                validate $file
                if [ $? -eq 0 ]; then
                        test "x$file" = "x${current_datadir}" && exit 0
                        change_ln $file
                        clean_up
                        exit 0
                fi

        done
}

function validate() {
        [ -e $1/$valid_file ] && return 0
        return 1
}

function change_ln() {
        [ -e $datadir ] && rm $datadir
        ln -s $1 $datadir
}

function clean_up() {
        read_current
        for f in `ls -ra $homedir | grep "$appdir"_`; do
                test "x${homedir}/${f}" != "x${current_datadir}" && rm -rf $homedir/$f
        done

}

post_validate() {
        test -e $datadir/$valid_file && rm -rf $datadir/*
}

if [ -e $datadir ]; then
        iterate
else
        create_initial
fi

# post_validate
