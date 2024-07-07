import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Calendar from './components/Calendar';
import RoomSelection from './components/RoomSelection';
import TimeSlotSelection from './components/TimeSlotSelection';
import BookingConfirmation from './components/BookingConfirmation';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Calendar />} />
        <Route path="/rooms/:date" element={<RoomSelection />} />
        <Route path="/slots/:roomId/:date" element={<TimeSlotSelection />} />
        <Route path="/confirm/:roomId/:date/:slots" element={<BookingConfirmation />} />
      </Routes>
    </Router>
  );
};

export default App;
