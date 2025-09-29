import React, { useState } from 'react';
import axios from 'axios';
import './GetStudent.css';

function GetStudent() {
  const [id, setId] = useState('');
  const [student, setStudent] = useState(null);
  const [message, setMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.get(`http://localhost:8080/student/${id}`);
      setStudent(res.data);
      setMessage('');
    } catch (err) {
      setMessage('Student not found');
      setStudent(null);
    }
  };

  return (
    <div className="form-container">
      <h2>Get Student by ID</h2>
      {message && <p className="message">{message}</p>}
      <form onSubmit={handleSubmit}>
        <label>Student ID:</label>
        <input
          type="number"
          value={id}
          onChange={(e) => setId(e.target.value)}
          required
        />
        <button type="submit">Get Student</button>
      </form>

      {student && (
        <div className="student-details">
          <p><strong>ID:</strong> {student.id}</p>
          <p><strong>Name:</strong> {student.studentName}</p>
          <p><strong>Standard:</strong> {student.standard}</p>
        </div>
      )}
    </div>
  );
}

export default GetStudent;