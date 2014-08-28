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
	if [ -f /etc/dropbear/id_rsa.pub ]
	then
		echo "Public Key already exists."
	else
		echo "Creating Public Key.."
		dropbearkey -y -f /etc/dropbear/dropbear_rsa_host_key > /etc/dropbear/id_rsa.pub
		echo "DONE!"
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

