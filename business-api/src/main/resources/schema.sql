DROP TABLE IF EXISTS public.user_game CASCADE;
DROP TABLE IF EXISTS public.game CASCADE;
DROP TYPE IF EXISTS public.game_status CASCADE;
DROP TABLE IF EXISTS public.type CASCADE;
DROP TABLE IF EXISTS public.game_type CASCADE;
DROP TABLE IF EXISTS public.platform CASCADE;
DROP TABLE IF EXISTS public.release_date CASCADE;
DROP TABLE IF EXISTS public.user CASCADE;
DROP TABLE IF EXISTS public.game_review CASCADE;
DROP TABLE IF EXISTS public.user_wishlist CASCADE;

DROP TABLE IF EXISTS comment CASCADE;
DROP TABLE IF EXISTS post CASCADE;
DROP TABLE IF EXISTS game_topic CASCADE;

CREATE TYPE "game_status" AS ENUM (
  'not started',
  'started',
  'finished',
  'ragequit',
  'won''t continue'
);

CREATE TABLE IF NOT EXISTS "game" (
  "id" SERIAL PRIMARY KEY NOT NULL,
  "name" varchar NOT NULL,
  "publisher" varchar,
  "developer" varchar,
  "description" varchar,
  "igdb_id" varchar,
  "price" float
);

CREATE TABLE IF NOT EXISTS "type" (
  "id" SERIAL PRIMARY KEY,
  "libelle" varchar
);

CREATE TABLE IF NOT EXISTS "game_type" (
  "id" SERIAL PRIMARY KEY,
  "id_game" int,
  "id_type" int
);

CREATE TABLE IF NOT EXISTS "platform" (
  "id" SERIAL PRIMARY KEY,
  "libelle" varchar
);

CREATE TABLE IF NOT EXISTS "release_date" (
  "id" SERIAL PRIMARY KEY,
  "id_platform" int,
  "id_game" int,
  "released_at" timestamp
);

CREATE TABLE IF NOT EXISTS "user" (
  "id" SERIAL PRIMARY KEY,
  "email" varchar NOT NULL,
  "password" varchar NOT NULL,
  "username" varchar UNIQUE NOT NULL,
  "profile_picture" varchar
);

CREATE TABLE IF NOT EXISTS "user_game" (
  "id" SERIAL PRIMARY KEY,
  "id_user" int,
  "id_game" int,
  "bought_at" int,
  "sold_at" int,
  "status" varchar,
  "created_at" timestamp,
  "playtime" int
);

CREATE TABLE IF NOT EXISTS "game_review" (
  "id" SERIAL PRIMARY KEY,
  "id_user" int,
  "id_game" int,
  "rate" int,
  "review" varchar
);

CREATE TABLE IF NOT EXISTS "user_wishlist" (
    "id" SERIAL PRIMARY KEY,
    "id_user" int,
    "id_game" int
);

CREATE TABLE game_topic
(
    "id"   int PRIMARY KEY,
    "name" varchar
);

CREATE TABLE post
(
    "id" SERIAL PRIMARY KEY,
    "title" varchar,
    "author"  varchar,
    "content" varchar,
    "id_gametopic" int
);

CREATE TABLE comment
(
    "id"      SERIAL PRIMARY KEY,
    "author"  varchar,
    "content" varchar,
    "id_post" int
);

ALTER TABLE "user_wishlist" ADD FOREIGN KEY ("id_user") REFERENCES "user" ("id");

ALTER TABLE "user_wishlist" ADD FOREIGN KEY ("id_game") REFERENCES "game" ("id");

ALTER TABLE "game_type" ADD FOREIGN KEY ("id_game") REFERENCES "game" ("id");

ALTER TABLE "game_type" ADD FOREIGN KEY ("id_type") REFERENCES "type" ("id");

ALTER TABLE "release_date" ADD FOREIGN KEY ("id_platform") REFERENCES "platform" ("id");

ALTER TABLE "release_date" ADD FOREIGN KEY ("id_game") REFERENCES "game" ("id");

ALTER TABLE "user_game" ADD FOREIGN KEY ("id_user") REFERENCES "user" ("id");

ALTER TABLE "user_game" ADD FOREIGN KEY ("id_game") REFERENCES "game" ("id");

ALTER TABLE "game_review" ADD FOREIGN KEY ("id_user") REFERENCES "user" ("id");

ALTER TABLE "game_review" ADD FOREIGN KEY ("id_game") REFERENCES "game" ("id");

ALTER TABLE comment ADD FOREIGN KEY ("id_post") REFERENCES post ("id");

ALTER TABLE post ADD FOREIGN KEY ("id_gametopic") REFERENCES game_topic ("id");
