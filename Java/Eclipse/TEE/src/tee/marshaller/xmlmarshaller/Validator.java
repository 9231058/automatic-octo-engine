package tee.marshaller.xmlmarshaller;

import java.util.List;

import tee.domain.exceptions.MarshallerException;

public class Validator {
	private List<Item> items;

	private int teeTagNumber;
	private int executorTagNumber;
	private int scheduleTagNumber;
	private int tasksTagNumber;
	private int taskTagNumber;
	private int basePackageTagNumber;

	public Validator(List<Item> items) {
		this.items = items;
	}

	public void validate() throws MarshallerException {
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			switch (item.getName()) {
			case "tee":
				teeTagNumber++;
				validateTee(item);
				break;
			case "executor":
				executorTagNumber++;
				validateExecutor(item);
				break;
			case "schedule":
				scheduleTagNumber++;
				validateSchedule(item);
				break;
			case "tasks":
				tasksTagNumber++;
				validateTasks(item);
				break;
			case "base-package":
				basePackageTagNumber++;
				validateBasePackage(item);
				break;
			case "task":
				taskTagNumber++;
				validateTask(item);
				break;
			}
			System.out.println(item.getName() + " " + item.getData());
		}
	}

	private void validateTee(Item item) throws MarshallerException {
		if (item.getAttrSize() != 0) {
			throw new MarshallerException("\"tee\" tag worong attribute(s)");
		}
	}

	private void validateExecutor(Item item) throws MarshallerException {
		if (item.getAttrSize() != 0) {
			throw new MarshallerException(
					"\"executor\" tag worong attribute(s)");
		}
		if (teeTagNumber != executorTagNumber) {
			throw new MarshallerException("\"tee\", \"executor\" tag missed");
		}
	}

	private void validateSchedule(Item item) throws MarshallerException {
		if (executorTagNumber != scheduleTagNumber) {
			throw new MarshallerException(
					"\"executor\", \"schedule\" tag missed");
		}
		if (!item.contains("type")) {
			throw new MarshallerException("\"type\" attribute missed");
		}
		String type = item.getAttr("type");
		if (type.equalsIgnoreCase("daily")) {
		} else if (type.equalsIgnoreCase("weekly")) {
			if (!item.contains("time")) {
				throw new MarshallerException("\"time\" attribute missed");
			}
			String time = item.getAttr("time");
			if (!item.contains("days")) {
				throw new MarshallerException("\"days\" attribute missed");
			}
			String[] days = item.getAttr("days").split(",");
		} else if (type.equalsIgnoreCase("hourly")) {

		} else {
			throw new MarshallerException("wrong \"type\" attribute");
		}
	}

	private void validateTasks(Item item) throws MarshallerException {
		if (item.getAttrSize() != 0) {
			throw new MarshallerException("\"tasks\" tag worong attribute(s)");
		}
		if (teeTagNumber != tasksTagNumber
				|| executorTagNumber != tasksTagNumber) {
			throw new MarshallerException(
					"\"tee\", \"executer\", \"tasks\" tag missed");
		}
	}

	private void validateBasePackage(Item item) throws MarshallerException {
		if (item.getData() == "") {
			throw new MarshallerException("\"base-package\" has no content");
		}
	}

	private void validateTask(Item item) throws MarshallerException {
		if (!item.contains("id")) {
			throw new MarshallerException("\"id\" attribute missed");
		}
		if (!item.contains("class")) {
			throw new MarshallerException("\"class\" attribute missed");
		}
		if (!item.contains("method")) {
			throw new MarshallerException("\"method\" attribute missed");
		}
		if (basePackageTagNumber == 0) {
			throw new MarshallerException("\"base-package\" tag missed");
		}
	}

}
