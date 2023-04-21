+User(UUID: id, varchar: username, varchar: password, varchar: email, varchar: phone, varchar: avatar, boolean disabled) - Таблица пользователя
+Role(UUID: id, varchar: name) - Таблица ролей (USER, MODERATOR, SPEAKER, WATCHER)
+Discipline(UUID: id, varchar: name) - Таблица дисциплин (Java SE начальный курс, Python начальный курс, ...)
Course(UUID: id, varchar: name, date: startDate, date: endDate, UUID: disciplineId) - Таблица курса (Java SE начальный курс будет проходить с ...г. по ...г.)
Student(UUID: userId, UUID: courseId, date: courseStartDate) -  Таблица студента (Студентом может стать любой пользователь) (в таблице составной ключ userId+courseId)
Speaker(UUID: userId, UUID: courseId, date: courseStartDate) - Таблица преподавателя на курсах (спикером может быть только User.getRole() -> SPEAKER) (в таблице составной ключ userId+courseId)
Practice(UUID: disciplineId, varchar: name, varchar: description) - Таблица практических заданий
Homework(UUID: userId, UUID: watcherId, UUID: practice, boolean: done) - Таблица домашних заданий (в таблице составной ключ userId+practice)

Связи:
User -> Role (ManyToMany) - доп таблица UserRoles (UUID: userId, UUID: roleId)
Discipline -> Course (OneToMany)
Discipline -> Practice (OneToMany)