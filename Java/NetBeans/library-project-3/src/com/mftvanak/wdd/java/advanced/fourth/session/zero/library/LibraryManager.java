package com.mftvanak.wdd.java.advanced.fourth.session.zero.library;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class LibraryManager {

	private PolicyManager policyManager;
	private Map<Member, List<Book>> borrowHistory;

	public LibraryManager(PolicyManager policyManager) {
		this.policyManager = policyManager;
	}

	public void borrow(Member m, Book b) {
		if (policyManager.checkMemberForBorrow(m, b)) {
			if (!borrowHistory.containsKey(m)) {
				borrowHistory.put(m, new ArrayList<Book>());
			}
			List<Book> books = borrowHistory.get(m);
			books.add(b);
		}
	}

	// ...
}
