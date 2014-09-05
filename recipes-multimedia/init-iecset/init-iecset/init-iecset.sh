### BEGIN INIT INFO
# Provides:             init-iecset
# Required-Start:       
# Required-Stop:      
# Default-Start:
# Default-Stop:
# Short-Description:  Initializes IEC958 status bits
### END INIT INFO

test -e /etc/default/iecset -o -e /etc/iecset.conf || exit 0

test -e /etc/default/iecset && . /etc/default/iecset
test -e /etc/iecset.conf && . /etc/iecset.conf

iecset ${IECSET_BITS}
