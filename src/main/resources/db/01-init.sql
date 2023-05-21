USE `lead_service_db`;


create table token
(
    id              INTEGER UNSIGNED AUTO_INCREMENT,
    name            VARCHAR(250),
    token           VARCHAR(250),
    path            VARCHAR(250),
    isAdmin         BOOLEAN,
    expiresAt       DATETIME,
    createdAt       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB;

create table lead_meta
(
    id              INTEGER UNSIGNED AUTO_INCREMENT,
    leadId          INTEGER UNSIGNED,
    type            VARCHAR(100),
    dialFire        boolean,
    klickTipp       boolean,
    dialFireError   boolean,
    klickTippError  boolean,
    createdAt       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt       DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    CONSTRAINT unique_type_leadId unique (leadId, type)
)
    ENGINE = InnoDB;

create table finanzen_lead
(

    id              INTEGER UNSIGNED AUTO_INCREMENT,
    externalId      VARCHAR(250),
    salutation      VARCHAR(250),
    gender          VARCHAR(250),
    firstName       VARCHAR(250),
    lastName        VARCHAR(250),
    dob             VARCHAR(250),
    occupation      VARCHAR(250),
    phone           VARCHAR(250),
    email           VARCHAR(250),
    subject         VARCHAR(250),
    street          VARCHAR(250),
    postalCode      VARCHAR(250),
    city            VARCHAR(250),
    state           VARCHAR(250),
    company         VARCHAR(250),
    data            TEXT,
    leadCreatedAt   DATE,
    createdAt       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt       DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB;


create table egentic_lead
(
    id                  INTEGER UNSIGNED AUTO_INCREMENT,
    salutation          VARCHAR(250),
    firstName           VARCHAR(250),
    lastName            VARCHAR(250),
    dob                 DATETIME,
    phone               VARCHAR(250),
    email               VARCHAR(250),
    street              VARCHAR(250),
    postalCode          VARCHAR(250),
    city                VARCHAR(250),
    regTime             DATETIME,
    regId               VARCHAR(250),
    regIp               VARCHAR(250),
    smsVerify           DATETIME,
    question            VARCHAR(250),
    answer              VARCHAR(250),
    createdAt           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt           TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)

)
    ENGINE = InnoDB;


create table animal_lead
(
    id                  INTEGER UNSIGNED AUTO_INCREMENT,
    firstName           VARCHAR(250),
    lastName            VARCHAR(250),
    dob                 DATETIME,
    phone               VARCHAR(250),
    email               VARCHAR(250),
    street              VARCHAR(250),
    zipCode             VARCHAR(250),
    city                VARCHAR(250),
    animalType          VARCHAR(250),
    animalName          VARCHAR(250),
    animalBreed         VARCHAR(250),
    animalGender        VARCHAR(250),
    animalAge           VARCHAR(250),
    animalCastration    VARCHAR(250),
    selfContribution    VARCHAR(250),
    policyType          VARCHAR(250),
    extraInfo           VARCHAR(250),
    createdAt           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt           TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)

)
    ENGINE = InnoDB;

create table teeth_lead
(
    id                  INTEGER UNSIGNED AUTO_INCREMENT,
    firstName           VARCHAR(250),
    lastName            VARCHAR(250),
    dob                 DATETIME,
    phone               VARCHAR(250),
    email               VARCHAR(250),
    street              VARCHAR(250),
    zipCode             VARCHAR(250),
    city                VARCHAR(250),
    birthday            VARCHAR(250),
    publicInsurance     VARCHAR(250),
    alreadyInTreatment  VARCHAR(250),
    missingTeeth        VARCHAR(250),
    coverage            VARCHAR(250),
    extraInfo           VARCHAR(250),
    createdAt          TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt          TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)

)
    ENGINE = InnoDB;

create table house_lead
(
    id                  INTEGER UNSIGNED AUTO_INCREMENT,
    firstName           VARCHAR(250),
    lastName            VARCHAR(250),
    dob                 DATETIME,
    phone               VARCHAR(250),
    email               VARCHAR(250),
    street              VARCHAR(250),
    zipCode             VARCHAR(250),
    city                VARCHAR(250),
    houseType           VARCHAR(250),
    buildYear           VARCHAR(250),
    objectStreet        VARCHAR(250),
    objectZipCode       VARCHAR(250),
    objectCity          VARCHAR(250),
    objectHouseNumber   VARCHAR(250),
    completeHouse       VARCHAR(250),
    area                VARCHAR(250),
    basement            VARCHAR(250),
    basementArea        VARCHAR(250),
    levelTop            VARCHAR(250),
    protectedHouse      VARCHAR(250),
    solarThermal        VARCHAR(250),
    photovoltaic        VARCHAR(250),
    swimmingPool        VARCHAR(250),
    vacationHouse       VARCHAR(250),
    extraHouse          VARCHAR(250),
    extraHouseArea      VARCHAR(250),
    damageFiveYears     VARCHAR(250),
    damageAmount        VARCHAR(250),
    extraInfo           VARCHAR(250),
    createdAt           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updatedAt           TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id)

)
    ENGINE = InnoDB;
