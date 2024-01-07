package com.mftvanak.classes.jee.zero.validation;

import com.mftvanak.classes.jee.zero.controllers.DefaultSignUpData;
import com.mftvanak.classes.jee.zero.controllers.SignUpData;
import com.mftvanak.classes.jee.zero.dao.UserDao;
import com.mftvanak.classes.jee.zero.dao.UserDaoFactory;

public class DefaultSignUpRequestValiator implements SignUpRequestValidator {

	@Override
	public ValidationResult validate(SignUpData data) {
		ValidationResult result = new ValidationResult();
		if (data instanceof DefaultSignUpData) {
			DefaultSignUpData suData = (DefaultSignUpData) data;
			if (suData.getName() == null || suData.getName().equals("")) {
				result.addError("name", "Name should be provided");
			}
			if (suData.getFamily() == null || suData.getFamily().equals("")) {
				result.addError("family", "Family should be provided");
			}
			if (suData.getPassword() == null || suData.getPassword().equals("")) {

			}
			if (suData.getPasswordConfirmation() == null
					|| suData.getPasswordConfirmation().equals("")) {
				result.addError("passwordConfermation", "Password confermation should be provided");
			}
			UserDao userDao = UserDaoFactory.createSingleton();
			if (userDao.getUserByUsername(suData.getUsername()) != null) {
				result.addError("username", "Username exists.");
			}
			return result;
		}
		throw new RuntimeException(
				"SignUpData implementation is not supported."); // This is
																// wrong !!!
	}
}
