package util;

import controller.MovieManagementController;
import pe.hsf301.fall24.pojo.Account;

public class RoleHandler {

	public static void checkRole(MovieManagementController smController, Account account) {
		// 1: admin, 2: staff, 3: manager, 4: customer
		// if user is a admin or manager, save to a temporary variable
		switch (account.getRoleId()) {
		case 1: {
			smController.setRoleID(1);
			break;
		}
		case 2: {
//			smController.setRoleID(2);
			break;
		}
		case 3: {
			smController.setRoleID(3);
			break;
		}
		case 4: {
//			smController.setRoleID(4);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + account.getRoleId());
		}
	}

}
