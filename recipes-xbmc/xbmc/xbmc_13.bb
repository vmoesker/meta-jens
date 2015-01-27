require xbmc.inc
SRCREV="dc641448862fe68dce250fd8808b54b7b7af2a12"
SRC_URI = "git://github.com/rdm-dev/xbmc.git;rev=${SRCREV} \
	file://rss.patch \
	file://vp8ts.patch \
"
PR = "${INC_PR}.2"
