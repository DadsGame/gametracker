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

INSERT INTO public.user (name, firstname, email, password, username, profile_picture) VALUES ('toyota', 'yaris', 'toyota@yaris.com', '123456', 'tomota', null);
INSERT INTO public.user (name, firstname, email, password, username, profile_picture) VALUES ('tutur', 'elfuego', 'tutur@elfuego.com', '000000', 'tutur', null);

INSERT INTO user_game (id_user, id_game, bought_at, on_wishlist, status, created_at, playtime) VALUES (1, 1, 10.92, false, 'started', '2019-11-11 19:10:25-07', 10);
INSERT INTO user_wishlist (id_user, id_game) VALUES (2, 1);