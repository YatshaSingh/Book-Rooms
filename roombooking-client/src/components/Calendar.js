import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const Calendar = () => {
  const [selectedDate, setSelectedDate] = useState('');

  const handleDateSelect = (date) => {
    setSelectedDate(date);
  };

  return (
    <div>
      <h2>Select Date:</h2>
      <input type="date" value={selectedDate} onChange={(e) => handleDateSelect(e.target.value)} />
      <Link to={`/rooms/${selectedDate}`}>Search Rooms</Link>
    </div>
  );
};

export default Calendar;
