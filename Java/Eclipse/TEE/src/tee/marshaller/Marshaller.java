package tee.marshaller;

import tee.domain.exceptions.MarshallerException;

public interface Marshaller {
	void validate() throws MarshallerException;

	void marshal();
}
