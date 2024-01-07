package com.mftvanak.wdd.java.advanced.fourth.session.zero.library;

public class BypassPolicyManager implements PolicyManager {

	@Override
	public boolean checkMemberForBorrow(Member m, Book b) {
		return true;
	}
}
