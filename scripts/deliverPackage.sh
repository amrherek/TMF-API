#!/bin/bash
# Script for making a delivery package

export COUNTRY=otn
export VERSION=1.14.3
#export http_proxy=http://nrs-proxy.ad-subs.w2k.francetelecom.fr:3128/

downloadWar() {
	API=$1
	URL="http://maven2.rd.francetelecom.fr/proxy/content/repositories/inhouse/com/orange/mea/apisi/$API/$COUNTRY-$API/$VERSION/$COUNTRY-$API-$VERSION.war"
	echo "download war for $API: $URL"
	curl -O $URL
}

init() {
	DIR=$COUNTRY-APIs-$VERSION
	mkdir $DIR
	cd $DIR
	mkdir APIs
	cd APIs
}

init
downloadWar party
downloadWar product-inventory
downloadWar product-ordering
downloadWar billing-account
downloadWar bucket-balance
downloadWar customer-bill
downloadWar product-catalog
downloadWar eligibility
downloadWar payment

