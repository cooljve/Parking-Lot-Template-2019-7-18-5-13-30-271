create table `parking_lot` (
  `parking_lot_name` varchar primary key ,
  `capacity` int ,
  `location` varchar ,
  unique key (`parking_lot_name`)
);