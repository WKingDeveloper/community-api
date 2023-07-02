CREATE TABLE IF NOT EXISTS `Board` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(16) NOT NULL,
    `parentBoardId` bigint DEFAULT NULL,
    `displayOrder` int NOT NULL,
    `status` int NOT NULL,
    `version` bigint DEFAULT 0,
    `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `deletedAt` timestamp DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_board_parentBoardId` FOREIGN KEY (`parentBoardId`) REFERENCES `Board` (`id`)
    );

CREATE TABLE IF NOT EXISTS `Post` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `title` varchar(512) NOT NULL,
    `content` text NOT NULL,
    `boardId` bigint NOT NULL,
    `likes` int DEFAULT 0,
    `views` int DEFAULT 0,
    `status` int NOT NULL,
    `version` bigint DEFAULT 0,
    `createdAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `deletedAt` timestamp DEFAULT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_Post_boardId` FOREIGN KEY (`boardId`) REFERENCES `Board` (`id`)
    );