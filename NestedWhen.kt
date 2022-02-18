private fun mapAgreementServiceType(contractAsset: ContractAsset) =
        when (contractAsset.sourceSystem) {
            JAZZ ->
                    when (contractAsset.contractTypeCode) {
                        "UFIX" -> "mix"
                        "ASP", "APN", "IT", "FLIP_FIX", "FLIP_GSM" -> contractAsset.contractTypeCode
                        else -> "postpaid"
                    }
            DOMINO -> "prepaid" // TODO : if opcache service_type is needed ->
            // contractAsset.contractTypeCode
            SIEBEL ->
                    if (contractAsset.contractLevel == ContractAsset.ContractLevelEnum.ACCOUNT)
                            ACCOUNT_SERVICE_TYPE
                    else
                            when (contractAsset.contractTypeCode?.uppercase()) {
                                "VOICE" -> "fixedvoice"
                                "TELEVISION" -> "fixedtv"
                                "INTERNET" -> "fixednet"
                                "NONTELCO" -> "insurance"
                                else -> contractAsset.contractTypeCode ?: ""
                            }
        }
