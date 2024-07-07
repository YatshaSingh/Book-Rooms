import React from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';

const BookingConfirmation = () => {
  const { roomId, date, slots } = useParams();

  const handleBooking = () => {
    axios.post(`http://localhost:8000/v1/api/bookroom`, null, { params: { RoomName: roomId, Date: date, TimeSlot: slots.split(',') } })
      .then(response => console.log('Booking successful:', response.data))
      .catch(error => console.error('Error booking room:', error));
  };

  return (
    <div>
      <h2>Confirm Booking:</h2>
      <p>Room: {roomId}</p>
      <p>Date: {date}</p>
      <p>Slots: {slots}</p>
      <button onClick={handleBooking}>Book Slots</button>
    </div>
  );
};

export default BookingConfirmation;
