DELETE FROM hotel_amenities;
DELETE FROM hotel;

INSERT INTO hotel (id, name, description, brand, house_number, street, city, country, post_code, phone, email, check_in, check_out, amenities) VALUES
(
  1,
  'DoubleTree by Hilton Minsk',
  'A great hotel in Minsk.',
  'Hilton',
  9,
  'Pobediteley Avenue',
  'Minsk',
  'Belarus',
  '220004',
  '+375 17 309-80-00',
  'doubletreeminsk.info@hilton.com',
  '14:00',
  '12:00',
  'Free WiFi,Free parking,Fitness center'
),
(
  2,
  'Moscow Marriott Grand Hotel',
  'A great hotel in Moscow.',
  'Marriott',
  26,
  'Tverskaya Street, Building 1',
  'Moscow',
  'Russia',
  '125009',
  '+7 495 937-00-00',
  'moscow.marriott@marriott.com',
  '15:00',
  '12:00',
  'Free WiFi,Non-smoking rooms'
);

INSERT INTO hotel_amenities (hotel_id, amenities) VALUES
(1, 'Free WiFi'),
(1, 'Free parking'),
(1, 'Fitness center');

INSERT INTO hotel_amenities (hotel_id, amenities) VALUES
(2, 'Free WiFi'),
(2, 'Non-smoking rooms');