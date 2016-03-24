package Model;

public class Result {
	public int statusID;
	public String message;
	
	public Object obj;

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		statusID = statusID;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		message = message;
	}

	public Result(int StatusID, String Message) {
		this.statusID = StatusID;
		this.message = Message;
	}

	public Result() {
	}

	public static Result Create(int StatusID, String Message) {
		return new Result(StatusID, Message);
	}

	/**
	 * �ɹ�
	 * 
	 * @param msg
	 * @return
	 */
	public static Result Success(String msg) {
		return Create(1, msg);
	}

	public static Result Success() {
		return Create(1, "");
	}

	/**
	 * ʧ��
	 * 
	 * @param msg
	 * @return
	 */
	public static Result Fail(String msg) {
		return Create(0, msg);
	}
}
