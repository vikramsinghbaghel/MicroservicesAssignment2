package com.nagarro.helpapp.demoentity;


public class RequestedItems {
	private int requestedItemId;
	private String requestStatus;
	private String requestedItemName;
	private String description;
	private String areaofOperation;
	private String receiverContactDetails;

	public RequestedItems() {
	
	}

	public RequestedItems(int requestedItemId, String requestStatus, String requestedItemName, String description,
			String areaofOperation, String receiverContactDetails) {
		super();
		this.requestedItemId = requestedItemId;
		this.requestStatus = requestStatus;
		this.requestedItemName = requestedItemName;
		this.description = description;
		this.areaofOperation = areaofOperation;
		this.receiverContactDetails = receiverContactDetails;
	}



	public int getRequestedItemId() {
		return requestedItemId;
	}

	public void setRequestedItemId(int requestedItemId) {
		this.requestedItemId = requestedItemId;
	}

	
	public String getRequestedItemName() {
		return requestedItemName;
	}

	public void setRequestedItemName(String requestedItemName) {
		this.requestedItemName = requestedItemName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getReceiverContactDetails() {
		return receiverContactDetails;
	}

	public void setReceiverContactDetails(String receiverContactDetails) {
		this.receiverContactDetails = receiverContactDetails;
	}



	public String getRequestStatus() {
		return requestStatus;
	}



	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}



	public String getAreaofOperation() {
		return areaofOperation;
	}



	public void setAreaofOperation(String areaofOperation) {
		this.areaofOperation = areaofOperation;
	}



	@Override
	public String toString() {
		return "RequestedItems [requestedItemId=" + requestedItemId + ", requestStatus=" + requestStatus
				+ ", requestedItemName=" + requestedItemName + ", description=" + description + ", areaofOperation="
				+ areaofOperation + ", receiverContactDetails=" + receiverContactDetails + "]";
	}
	
	

//	@Override
//	public String toString() {
//		return "RequestedItems [requestedItemId=" + requestedItemId + ", requestedItemType=" + requestedItemType
//				+ ", requestedItemName=" + requestedItemName + ", description=" + description + ", receiverAddress="
//				+ receiverAddress + ", receiverContactDetails=" + receiverContactDetails + "]";
//	}

}
