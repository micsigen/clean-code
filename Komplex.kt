private fun mapPendingStatus(
        xCahnnelId: String,
        status: String,
        originalStatus: String?,
        sourceSystem: ContractAsset.SourceSystemEnum?
): StatusEnum {
    return if (xCahnnelId == ONESHOP_CHANNEL) {
        StatusEnum.valueOf(status.uppercase())
    } else {
        if (sourceSystem == SIEBEL && originalStatus != null) {
            when (status) {
                "Pending_suspension", "Pending_reactivation", "Pending_terminate" ->
                        try {
                            StatusEnum.valueOf(originalStatus.uppercase())
                        } catch (e: IllegalArgumentException) {
                            StatusEnum.fromValue(originalStatus)
                        }
                else -> mapPendingStatus(xCahnnelId, status, null, sourceSystem)
            }
        } else {
            try {
                StatusEnum.valueOf(status.uppercase())
            } catch (e: IllegalArgumentException) {
                StatusEnum.fromValue(status)
            }
        }
    }
}
