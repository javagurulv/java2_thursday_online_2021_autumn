DROP TABLE IF EXISTS doctors CASCADE;
DROP TABLE IF EXISTS patients CASCADE;
DROP TABLE IF EXISTS visits CASCADE;
DROP TABLE IF EXISTS prescriptions CASCADE;

CREATE TABLE IF NOT EXISTS `doctors` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `surname` VARCHAR(100) NOT NULL,
  `speciality` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE IF NOT EXISTS `patients` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `surname` VARCHAR(100) NOT NULL,
  `personal_code` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`)
)

CREATE TABLE IF NOT EXISTS `visits` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `doctor_id` BIGINT NOT NULL,
  `patient_id` BIGINT NOT NULL,
  `date` TIMESTAMP NOT NULL,
  `description` VARCHAR(200) NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (`doctor_id`) REFERENCES `doctors`(`id`),
  FOREIGN KEY (`patient_id`) REFERENCES `patients`(`id`)
)

CREATE TABLE IF NOT EXISTS `prescriptions` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `doctor_id` BIGINT NOT NULL,
  `patient_id` BIGINT NOT NULL,
  `medication_name` VARCHAR(150) NOT NULL,
  `quantity` INTEGER NOT NULL,
  `date` DATE NOT NULL,
  `valid_till` DATE NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (`doctor_id`) REFERENCES `doctors`(`id`),
  FOREIGN KEY (`patient_id`) REFERENCES `patients`(`id`)
)