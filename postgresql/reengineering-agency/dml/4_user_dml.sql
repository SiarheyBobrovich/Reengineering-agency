\connect monitor

INSERT INTO security.users (username, password, enabled)
  values
  	('user',
    '$2a$10$37O7PaGkQWaoPpHLlWgoBO4z7xK.qrSNBRTBtxgq2S.2eeoINmzMW',
    true),
	('admin',
    '$2a$10$lj4msHJGrlsrWBrFlwaNlO5W51vWOs6McSoYTi7g8.GA0asKNyBjW',
    true);

INSERT INTO security.authorities (username, authority)
  values
  	('user', 'ROLE_USER'),
  	('admin', 'ROLE_ADMIN');