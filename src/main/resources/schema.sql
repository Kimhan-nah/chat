-- Create user table
CREATE TABLE `users` (
                        `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                        `name` VARCHAR(255) NOT NULL,
                        `is_deleted` BOOLEAN NOT NULL DEFAULT FALSE,
                        `created_at` DATETIME(6),
                        `modified_at` DATETIME(6)
);

-- Create chat_room table
CREATE TABLE `chat_room` (
                            `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                            `title` VARCHAR(255) NOT NULL,
                            `is_deleted` BOOLEAN NOT NULL DEFAULT FALSE,
                            `created_at` DATETIME(6),
                            `modified_at` DATETIME(6)
);

-- Create chat_room_user table
CREATE TABLE `chat_room_user` (
                            `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                            `user_id` BIGINT NOT NULL,
                            `chat_room_id` BIGINT NOT NULL,
                            `is_deleted` BOOLEAN NOT NULL DEFAULT FALSE,
                            `created_at` DATETIME(6),
                            `modified_at` DATETIME(6),
                            CONSTRAINT `uk_user_chat_room` UNIQUE (`user_id`, `chat_room_id`),
                            FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
                            FOREIGN KEY (`chat_room_id`) REFERENCES `chat_room` (`id`)
);

-- Create chat table
CREATE TABLE `chat` (
                        `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
                        `chat_room_user_id` BIGINT NOT NULL,
                        `contents` VARCHAR(5000),
                        `is_deleted` BOOLEAN NOT NULL DEFAULT FALSE,
                        `created_at` DATETIME(6),
                        `modified_at` DATETIME(6),
                        FOREIGN KEY (`chat_room_user_id`) REFERENCES `chat_room_user` (`id`)
);

-- Add indexes for foreign keys to improve query performance
-- CREATE INDEX idx_chat_room_user_user_id ON `chat_room_user` (`user_id`);
-- CREATE INDEX idx_chat_room_user_chat_room_id ON `chat_room_user` (`chat_room_id`);
-- CREATE INDEX idx_chat_chat_room_user_id ON `chat` (`chat_room_user_id`);
