INSERT INTO game (name, publisher, developer, description, price) VALUES ('skyrim', 'Bethesda Softworks', 'Bethesda Game Studios', 'L''Empire de Tamriel va bientôt sombrer. Le Haut-Roi de Bordeciel a été assassiné. Les alliances se forment selon les prétendants au trône. Ce conflit a malheureusement réveillé un mal bien plus grand et plus dangereux. Les Dragons, depuis longtemps oubliés des Elder Scrolls, reviennent à Tamriel. Le futur de Bordeciel et de l''Empire tout entier repose sur une prophétie évoquant le retour d''un fils de dragon : un héros possédant le pouvoir de la Voix, le seul et unique à être capable d''affronter les dragons.', 10.2);

INSERT INTO platform (libelle) VALUES ('pc');
INSERT INTO platform (libelle) VALUES ('switch');
INSERT INTO platform (libelle) VALUES ('playstation');
INSERT INTO platform (libelle) VALUES ('xbox');
INSERT INTO platform (libelle) VALUES ('mac');

INSERT INTO release_date (id_game, id_platform, released_at) VALUES (1, 1, '2011-11-11 19:10:25-07');
INSERT INTO release_date (id_game, id_platform, released_at) VALUES (1, 2, '2011-11-11 20:10:25-07');
INSERT INTO release_date (id_game, id_platform, released_at) VALUES (1, 3, '2011-11-11 21:10:25-07');
INSERT INTO release_date (id_game, id_platform, released_at) VALUES (1, 4, '2011-11-11 22:10:25-07');

INSERT INTO public.user (email, password, username, profile_picture) VALUES ('toyota@yaris.com', 'fef02686604dcb40463bcbc8e15917fdb336f29e100a0d26ade0e6fe1a5ace49a451dca6254922007fc4588d2e76ab6f6a7497493d5a981704215fdf22a5e4a6', 'tomota', null);
INSERT INTO public.user (email, password, username, profile_picture) VALUES ('tutur@elfuego.com', '000000', 'tutur', null);

INSERT INTO user_game (id_user, id_game, bought_at, status, created_at, playtime) VALUES (1, 1, 10.92, 'started', '2019-11-11 19:10:25-07', 10);
INSERT INTO user_wishlist (id_user, id_game) VALUES (2, 1);

INSERT INTO game_topic (id, name) values (1, 'Counter Strike');
INSERT INTO post (id_gametopic, author, title, content) values (1, 'John Doe', 'Here is the title', 'Here is my content');
INSERT INTO comment (id_post, author, content) values (1, 'Foo Doe', 'Here is my content answer');
