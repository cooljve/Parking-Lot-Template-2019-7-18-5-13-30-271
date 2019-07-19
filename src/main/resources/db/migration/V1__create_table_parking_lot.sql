create table `parking_lot` (
  `lot_id` int primary key auto_increment,
  `parking_lot_name` varchar ,
  `capacity` int ,
  `location` varchar ,
  unique key (`parking_lot_name`)
);