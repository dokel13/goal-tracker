INSERT INTO `badges` VALUES (1,'dayBadge');
INSERT INTO `badges` VALUES (2,'monthBadge');
INSERT INTO `badges` VALUES (3,'weekBadge');
INSERT INTO `badges` VALUES (4,'yearBadge');
INSERT INTO `badges` VALUES (5,'completedBadge');

INSERT INTO `users` VALUES (1,'admin@gmail.com','Oleksiy','$2a$10$E53OxINOA5l7o.qMn4jnZOf0L0fBSmBjGhgv07qNrn2XsZS0yXE/S','USER');
INSERT INTO `users` VALUES (2,'andriy@ukr.net','Андрій','$2a$10$1FSG/o46TXTZaYy2Ul9VaO6e2ZEafJOdVMwUJMRgBK15vUZtYK3Ea','USER');
INSERT INTO `users` VALUES (3,'barak@ukr.net','Барак','$2a$10$hb6NkHtwLdgUNzxK0bwNTOIbLTDeXd7hIn4QTUUi2flju9uO3onty','USER');
INSERT INTO `users` VALUES (4,'vasil@gmail.com','Vasil','$2a$10$bH1yqjVfqHwn2YoTMitq6eBHD654cR9qVaAr02x2v4zD0GibbEJcS','USER');
INSERT INTO `users` VALUES (5,'hryhorii@ukr.net','Григорій','$2a$10$wC/rmsWUSZsywZFES5jwneEzD.DVGhhjE/GMVCzNHCPY4K4KYQE6m','USER');
INSERT INTO `users` VALUES (6,'george@gmail.com','George Michael','$2a$10$9VPb5hCH3Uj3RaMXb3516OEMk/DSusYK01TFO.LQhrCYsNk2W8pT.','USER');
INSERT INTO `users` VALUES (7,'dmytro@ukr.net','Дмитро','$2a$10$FetvCnktniFBCDDboNhyG.7BeFRGugFPu5PojG6iDHS5T5yMooMf2','USER');
INSERT INTO `users` VALUES (8,'ewan@gmail.com','Ewan McGregor','$2a$10$xr3NUlzH/WAhr6JK9ehWaei2dcKMFdlSNOjSTgq8Zad/ywfXq8Fw.','USER');
INSERT INTO `users` VALUES (9,'yevhen@ukr.net','Євген Євгенович','$2a$10$Zr3sJMjtc4bjdKF76zdDyO9dOkH0Qxc7b6q2.hoKJGvr0emSMUIyi','USER');
INSERT INTO `users` VALUES (10,'john@gmail.com','John McClane','$2a$10$65FPQ6SoyteUfEnpWtXFiOi1lOd.yv0cQhsfXp4gEM1zgGVcUmNTC','USER');
INSERT INTO `users` VALUES (11,'zakhar@ukr.net','Захар','$2a$10$8rKayRCwUfwCqAeD.x6TH.DBJZFb.iyIda0WeP0LY6yfQ4O5viXu2','USER');
INSERT INTO `users` VALUES (12,'ihor@gmail.com','Ihor','$2a$10$UZg.i8NHc9EghbvSzmlljuBvxfmnc2lAp7B6BTzworqUWjMREkpwu','USER');
INSERT INTO `users` VALUES (13,'max@gmail.com','Max','$2a$10$xB4Q8UuxhN6pE0XBj5I30uw1vUe0mRmT3p3aLqdndDW8qweoTPGaG','USER');
INSERT INTO `users`	VALUES (14,'nazar@ukr.net','Назар','$2a$10$HqCNXBf0hrEURC91zLnvPuAVsXxbhMcjW7jUcoovEMxhjnA9hpIVO','USER');
INSERT INTO `users`	VALUES (15,'tommy@gmail.com','Tommy Wiseau','$2a$10$x.egx66qBqksLrGdO3thx.VUHUgSKfUCWmgmPhwBKj6XaL9lucs1u','USER');

INSERT INTO `users_users` VALUES
                                 (2,3), (2,4), (2,5),
                                 (2,8), (5,7), (5,8),
                                 (9,10), (9,11), (9,12),
                                 (13,14), (13,15);

INSERT INTO `users_badges` VALUES
                                  (2,1), (2,2), (2,3),
                                  (3,2), (3,3), (3,4),
                                  (4,3), (4,4), (4,5),
                                  (5,1), (5,2),
                                  (6,2), (6,3), (6,4),
                                  (7,3), (7,4), (7,5),
                                  (8,1), (8,2), (8,3),
                                  (9,2), (9,3),
                                  (10,3), (10,4), (10,5),
                                  (11,1), (11,2), (11,3),
                                  (12,2), (12,3), (11,4),
                                  (13,3), (13,4), (13,5);
