import React, { useState, useEffect } from 'react';
import { useParams, Link } from 'react-router-dom';
import axios from 'axios';
import './RoomSelection.css'; // Import your CSS file

const RoomSelection = () => {
  const { date: paramDate } = useParams();
  const [rooms, setRooms] = useState({});
  const [selectedRoom, setSelectedRoom] = useState(null);
  const [selectedSlots, setSelectedSlots] = useState([]);
  const [confirmationMessage, setConfirmationMessage] = useState('');
  const [availableDates, setAvailableDates] = useState([]);
  const [isLoading, setIsLoading] = useState(false);
  const [selectedDate, setSelectedDate] = useState(paramDate || '');

  useEffect(() => {
    fetchAvailableDates();
  }, []);

  useEffect(() => {
    if (selectedDate) {
      fetchRoomAvailability(selectedDate);
    }
  }, [selectedDate]);

  const fetchAvailableDates = () => {
    setIsLoading(true);
    axios.get(`http://localhost:8000/v1/api/GetAvailableDates`)
      .then(response => {
        setAvailableDates(response.data);
        setIsLoading(false);
      })
      .catch(error => {
        console.error('Error fetching available dates:', error);
        setIsLoading(false);
      });
  };

  const fetchRoomAvailability = (selectedDate) => {
    setIsLoading(true);
    axios.get(`http://localhost:8000/v1/api/CheckAvailability?Date=${selectedDate}`)
      .then(response => {
        setRooms(response.data);
        setIsLoading(false);
      })
      .catch(error => {
        console.error('Error fetching rooms:', error);
        setIsLoading(false);
      });
  };

  const handleDateSelect = (selectedDate) => {
    setSelectedDate(selectedDate);
    setSelectedRoom(null); // Reset selected room when date changes
    setSelectedSlots([]); // Reset selected slots when date changes
    setConfirmationMessage('');
  };

  const handleRoomSelect = (roomName) => {
    if (selectedRoom === roomName) {
      setSelectedRoom(null); // Collapse the room if already selected
    } else {
      setSelectedRoom(roomName);
      setSelectedSlots([]); // Reset selected slots when room changes
      setConfirmationMessage('');
    }
  };

  const handleSlotSelect = (slot) => {
    const updatedSlots = [...selectedSlots, slot];
    setSelectedSlots(updatedSlots);
  };

  const formatSelectedSlots = (slots) => {
    return slots.join(',');
  };

  const handleBooking = () => {
    const formattedSlots = formatSelectedSlots(selectedSlots);
    axios.post(`http://localhost:8000/v1/api/bookroom`, null, {
      params: {
        RoomName: selectedRoom,
        Date: selectedDate,
        TimeSlot: formattedSlots
      }
    })
      .then(response => {
        setConfirmationMessage(`Booking successful for ${selectedRoom} on ${selectedDate} at ${formattedSlots}.`);
        // Example of navigating using history object directly
        window.location.href = `/rooms/${selectedDate}`;
      })
      .catch(error => {
        console.error('Error booking room:', error);
        setConfirmationMessage('Error booking room. Please try again.');
      });
  };

  return (
    <div className="room-container">
      <h2>Select Date:</h2>
      <input type="date" value={selectedDate} onChange={(e) => handleDateSelect(e.target.value)} />
      <button className="search-button" onClick={() => window.location.href = `/rooms/${selectedDate}`}>
        Search Rooms
      </button>

      {isLoading ? (
        <p>Loading...</p>
      ) : (
        <div>
          {Object.keys(rooms).map(roomName => (
            <div key={roomName} className={`room-card ${selectedRoom === roomName ? 'selected' : ''}`}>
              <div
                className="room-info"
                onClick={() => handleRoomSelect(roomName)}
              >
                <h3>{roomName}</h3>
                <p>Amenities: {rooms[roomName].amenities.join(', ')}</p>
                <p>Seat Capacity: {rooms[roomName].seatCapacity}</p>
              </div>
              {selectedRoom === roomName && (
                <div className="time-slots">
                  <ul>
                    {rooms[roomName].slots.map(slot => (
                      <li key={slot} className="time-slot">
                        <button
                          className={selectedSlots.includes(slot) ? 'disabled' : ''}
                          onClick={() => handleSlotSelect(slot)}
                          disabled={selectedSlots.includes(slot)}
                        >
                          {slot}
                        </button>
                      </li>
                    ))}
                  </ul>
                  <button
                    className="book-button"
                    onClick={handleBooking}
                    disabled={selectedSlots.length === 0}
                  >
                    Book Slots
                  </button>
                  {confirmationMessage && (
                    <div className="booking-card">
                      <p>{confirmationMessage}</p>
                    </div>
                  )}
                </div>
              )}
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default RoomSelection;
