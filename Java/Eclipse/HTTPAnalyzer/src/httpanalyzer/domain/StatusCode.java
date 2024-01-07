package httpanalyzer.domain;

public enum StatusCode {
	OK(200), Redirect(303), NotFound(404);
	private int statusCode;

	private StatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getStatusCode() {
		return statusCode;
	}
}
