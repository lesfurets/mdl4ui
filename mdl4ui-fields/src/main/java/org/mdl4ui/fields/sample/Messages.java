package org.mdl4ui.fields.sample;

import org.ez18n.Message;
import org.ez18n.MessageBundle;

@MessageBundle
public interface Messages {

	@Message(value = "Love Me Tender", mobile = "Love Me True")
	String loveMeTender();

	@Message("I love {0}")
	String doYouLove(String name);

}
