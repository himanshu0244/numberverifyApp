    CREATE TABLE `CONTACT_NUMBER` (
     `id` bigint(20) NOT NULL AUTO_INCREMENT,
      `number` varchar(100) NOT NULL, 
      `status` varchar(100) NOT NULL,
      `country_prefix` varchar(100),
      `country_code`  varchar(100),
      `country_name` varchar(100),
      `active_ind` int,
      `create_date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
      `created_by`  varchar(100),
       PRIMARY KEY (`number`)
      );
    
    
    