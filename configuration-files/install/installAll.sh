export PARTY_NAME_BASE="PARTY"
export PRODUCT_INVENTORY_NAME_BASE="PI"
export PRODUCT_ORDERING_NAME_BASE="PO"
export PRODUCT_CATALOG_NAME_BASE="PC"
export BUCKET_BALANCE_NAME_BASE="BB"
export CUSTOMER_BILL_NAME_BASE="CB"
export BILLING_ACCOUNT_NAME_BASE="BA"
export ELIGIBILITY_NAME_BASE="EL"
export PAYMENT_NAME_BASE="PA"

export GROUP_BASE="group"

usage()
{
	echo "Usage : $0 -p <trigramePays> -o <version>"
	exit 1
}

check_params()
{
	while [ "$#" -gt 0 ]
	do
	case $1 in
	 -p)
		export PAYS=$2
		echo "PAYS = $PAYS"
		shift
		shift
		;;
	 -o)
		export VERSION=$2
		echo "VERSION = $VERSION"
		shift
		shift
		;;
	*)
		echo "what is $1 ?"
		usage
		;;
	esac
	done
  
	if [ -z "$PAYS" ]
	then
	  echo "Pays requis"
	  usage
	fi
	if [ -z "$VERSION" ]
	then
		export VERSION="1.0.0"
	fi
}

check_params $*

export PAYS_LOWER=`echo $PAYS | tr '[:upper:]' '[:lower:]'`
export GROUP=$PAYS_LOWER$GROUP_BASE

install_api(){
	local API_NAME=$PAYS$1
	local USER=$2
	local LAST_PORT_DIGITS=$3
	# Create tomcat instance & user
	echo "./tomcat-create-instance.ksh -i $API_NAME  -o $VERSION -u $USER -g $GROUP -shutdown 98$3 -http 95$3 -https NO -ajp 97$3 -jmx 99$3"
	echo "./tomcat-switch-activation.ksh -i $API_NAME -o $VERSION"
	
	echo "su $USER"
	# Deploy application
	echo "OperateTomcatAll.ksh start $API_NAME"
	echo "OperateTomcatAll.ksh deploy $API_NAME manager"
	
	# Add ssh key for jenkins
	echo "cd"
    echo "mkdir .ssh"
    echo "chmod 700 .ssh"
	echo "cd .ssh"
	echo "cat << EOF > authorized_keys"
	echo "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQC9/0ntGVpf/EIWIvqn6542fqnqcwI78c9RGOmamQQFufLWr5L52G3H9A3w1Golee2ioCnE7Wzxxdqjfv+RAkWXVbRFG2DrSuNGthsy47qGTYDfV0cWhkjjED8haZaAV4pVbRrGOsxs9oXVfzmJwP2v6UEAhAJwC9TE0qhyLX6YGKYp+CPc7dB5WOSW1Txxq7jxd3ttmILNLYPlz5pHJoGqmqhaWNtDUrUCrQJYQZHSPHSDS8w6jwJOtWTliRjUTY2GTFtmWy4YhZq56WtUVWBLhog3nQZslIH3/qm0aOTnHjnfNLBCvbJn4koQIclKySHtAdUIf4YUxDLQWzf6E4CV deyb6792@EB-OR6285910"
	echo "EOF"
	echo "chmod 400 authorized_keys"
	
	echo "exit"
	
	#Add ssh access to user as root
	echo "usermod -aG acces_ssh $USER"
	local TOMCAT_CONF_LINE='<user username="tomcat" password="tomcat" roles="manager-gui"/>'
	local TOMCAT_CONF_FILE="/opt/application/$API_NAME/$VERSION/tomcat/00/conf/tomcat-users.xml"
	echo "sed -i 's|</tomcat-users>|$TOMCAT_CONF_LINE\n</tomcat-users>|g' $TOMCAT_CONF_FILE"
}

echo "cd /opt/tomcat/bin"

# PARTY
echo "============= PARTY ============="
install_api $PARTY_NAME_BASE adparty 00
echo "================================="

# PRODUCT INVENTORY
echo "======= PRODUCT INVENTORY ======="
install_api $PRODUCT_INVENTORY_NAME_BASE adtiapi 10
echo "================================="

# PRODUCT ORDERING
echo "======= PRODUCT  ORDERING ======="
install_api $PRODUCT_ORDERING_NAME_BASE adtiapo 20
echo "================================="

# PRODUCT CATALOG
echo "======== PRODUCT CATALOG ========"
install_api $PRODUCT_CATALOG_NAME_BASE adtiapc 30
echo "================================="

# BUCKET BALANCE
echo "======== BUCKET  BALANCE ========"
install_api $BUCKET_BALANCE_NAME_BASE adtiabb 40
echo "================================="

# CUSTOMER BILL
echo "========= CUSTOMER BILL ========="
install_api $CUSTOMER_BILL_NAME_BASE adtiacb 50
echo "================================="

# BILLING ACCOUNT
echo "======== BILLING ACCOUNT ========"
install_api $BILLING_ACCOUNT_NAME_BASE adtiaba 60
echo "================================="

# ELIGIBILITY
echo "========== ELIGIBILITY =========="
install_api $ELIGIBILITY_NAME_BASE adtiael 70
echo "================================="

# PAYMENT
echo "========== PAYMENT =========="
install_api $PAYMENT_NAME_BASE adtiapa 80
echo "================================="
