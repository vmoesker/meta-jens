# INIT INFO
# Provides:          Gen public key from /etc/dropbear/dropbear_rsa_host_key
# Required-Start:    mountvirtfs $local_fs
# Required-Stop:     $local_fs
# Should-Start:      dropbearkey
# Should-Stop:       
# Default-Start:     S
# Default-Stop:      0 6
# Short-Description: Raise network interfaces.
### END INIT INFO

PATH="/usr/local/sbin:/usr/local/bin:/sbin:/bin:/usr/sbin:/usr/bin"

case "$1" in
start)
	if [ -f /var/lib/dropbear/id_rsa.pub ]
	then
		echo "Public Key already exists."
	else
		echo "Creating Public Key.."
		dropbearkey -y -f /var/lib/dropbear/dropbear_rsa_host_key | grep "^ssh-rsa" > /var/lib/dropbear/id_rsa.pub
		echo "DONE! Check for NFS:"
		
		until ip addr show dev eth0 | grep -q inet
		do
			sleep 1
		done
		
		#mount nfs
		mkdir /tmp/prdkey/
		mount.nfs mpprdsrv:/srv/nfs/prdsrv_key /tmp/prdkey/
		#commit private key to git of production server
		(key=$(< /var/lib/dropbear/id_rsa.pub);mac=$(sed -e 's/://g' /sys/class/net/eth0/address); ssh -y -y -i /tmp/prdkey/id_rsa_dropbear hpkeyupload@mpprdsrv "cd ~/keys && git pull --rebase && echo $key > $mac && git add $mac && git commit -m '$mac added.'")

		
	fi
	;;
stop)
	;;

*)
	echo "Usage: /etc/init.d/networking {start|stop}"
	exit 1
	;;
esac

exit 0

