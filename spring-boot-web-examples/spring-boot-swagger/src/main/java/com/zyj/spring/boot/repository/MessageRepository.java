
package com.zyj.spring.boot.repository;

import java.util.List;

import com.zyj.spring.boot.model.Message;

public interface MessageRepository {

	List<Message> findAll();

	Message save(Message message);

	Message update(Message message);

	Message updateText(Message message);

	Message findMessage(Long id);

	void deleteMessage(Long id);

}
