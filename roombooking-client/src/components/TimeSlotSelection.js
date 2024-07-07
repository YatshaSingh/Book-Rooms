import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';

const TimeSlotSelection = () => {
  const { roomId, date } = useParams();
  const [availableSlots, setAvailableSlots] = useState([]);
  const [selectedSlots, setSelectedSlots] = useState([]);

  useEffect(() => {
    axios.get(`http://localhost:8000/v1/api/CheckAvailability?Date=${date}`)
      .then(response => setAvailableSlots(response.data[roomId]))
      .catch(error => console.error('Error fetching slots:', error));
  }, [roomId, date]);

  const handleSlotSelect = (slot) => {
    const updatedSlots = [...selectedSlots, slot];
    setSelectedSlots(updatedSlots);
  };

  return (
    <div>
      <h2>Select Time Slot:</h2>
      <ul>
        {availableSlots.map(slot => (
          <li key={slot}>
            <button onClick={() => handleSlotSelect(slot)}>{slot}</button>
          </li>
        ))}
      </ul>
      <Link to={`/confirm/${roomId}/${date}/${selectedSlots.join(',')}`}>Book Selected Slots</Link>
    </div>
  );
};

export default TimeSlotSelection;
