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

CREATE TYPE "game_status" AS ENUM (
  'not_started',
  'started',
  'finished',
  'ragequit'
);

CREATE TABLE IF NOT EXISTS "game" (
  "id" SERIAL PRIMARY KEY NOT NULL,
  "name" varchar NOT NULL,
  "publisher" varchar,
  "developer" varchar,
  "description" varchar,
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
  "name" varchar,
  "firstname" varchar,
  "email" varchar NOT NULL,
  "password" varchar NOT NULL,
  "username" varchar,
  "profile_picture" varchar
);

CREATE TABLE IF NOT EXISTS "user_game" (
  "id" SERIAL PRIMARY KEY,
  "id_user" int,
  "id_game" int,
  "bought_at" integer,
  "on_wishlist" boolean,
  "status" game_status,
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
