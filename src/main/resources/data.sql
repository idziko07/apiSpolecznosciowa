 INSERT INTO user(age, email, enabled, first_name, last_name, password, username) VALUES (25,'kopytko@re.pl',TRUE,'Barbara','Kopytko','$2a$10$thMPCsucgPsIARrMAN4A0umUEhIbEqBim9W1uayhpxjLSnLv4Bg5m','kopytko');
 INSERT INTO user(age, email, enabled, first_name, last_name, password, username) VALUES (33,'sledz@we.pl',TRUE,'Janusz','Sledz','$2a$10$x0Re9iS4vFecWwraUI4Q5u0Vb9696VMK1HuAidaqiwQc1LBFYqq7i','sledz');
 INSERT INTO user(age, email, enabled, first_name, last_name, password, username) VALUES (33,'sle@we.pl',TRUE,'Zenon','Polak','$2a$10$mcm2dlb2Pe8o4SMR73vineqOAuR3TM0IwHdoAZNxaMk.VPnqidvtK','polak');
 INSERT INTO user(age, email, enabled, first_name, last_name, password, username) VALUES (18,'gangst@op.pl',TRUE,'Tadeusz','Kowalski','$2a$10$rp0X0C.ZWDAbcgjlDZczHuIj9gssrJb5MxGvzn1OdOy9yaeFx96VC','gangster');
 INSERT INTO user(age, email, enabled, first_name, last_name, password, username) VALUES (45,'paula@op.pl',TRUE,'Paula','Rutkowska','$2a$10$Tn2LfR/8coBy8k5JrmFhFezK1vJ/OjENdGTqmQvUn5freYJH9RTzu','paula98');

 INSERT INTO user_role(role, username) VALUES ('ROLE_ADMIN', 'kopytko');
 INSERT INTO user_role(role, username) VALUES ('ROLE_USER', 'paula');
 INSERT INTO user_role(role, username) VALUES ('ROLE_USER', 'gangster');
 INSERT INTO user_role(role, username) VALUES ('ROLE_USER', 'sledz');

INSERT INTO friends (accept, user_friend_id, user_id) VALUES (FALSE , '2', '1');
INSERT INTO friends (accept, user_friend_id, user_id) VALUES (FALSE , '1', '5');
INSERT INTO friends (accept, user_friend_id, user_id) VALUES (FALSE , '1', '4');




-- INSERT INTO user(username, password,enabled) VALUES ('admin','1234',true);
-- INSERT INTO user(username, password,enabled) VALUES ('user','4321',true);
--
-- INSERT Into user_role(username, role) VALUES ('admin','ROLE_ADMIN');
-- INSERT Into user_role(username, role) VALUES ('user','ROLE_USER');