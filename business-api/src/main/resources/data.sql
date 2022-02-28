INSERT INTO game (name, publisher, developer, description, price) VALUES ('skyrim', 'Bethesda Softworks', 'Bethesda Game Studios', 'L''Empire de Tamriel va bientôt sombrer. Le Haut-Roi de Bordeciel a été assassiné. Les alliances se forment selon les prétendants au trône. Ce conflit a malheureusement réveillé un mal bien plus grand et plus dangereux. Les Dragons, depuis longtemps oubliés des Elder Scrolls, reviennent à Tamriel. Le futur de Bordeciel et de l''Empire tout entier repose sur une prophétie évoquant le retour d''un fils de dragon : un héros possédant le pouvoir de la Voix, le seul et unique à être capable d''affronter les dragons.', 10.2);

INSERT INTO platform (libelle) VALUES ('pc');
INSERT INTO platform (libelle) VALUES ('switch');
INSERT INTO platform (libelle) VALUES ('playstation');
INSERT INTO platform (libelle) VALUES ('xbox');
INSERT INTO platform (libelle) VALUES ('mac');

INSERT INTO game_on (id_game, id_platform) VALUES (1, 1);
INSERT INTO game_on (id_game, id_platform) VALUES (1, 2);
INSERT INTO game_on (id_game, id_platform) VALUES (1, 3);
INSERT INTO game_on (id_game, id_platform) VALUES (1, 4);