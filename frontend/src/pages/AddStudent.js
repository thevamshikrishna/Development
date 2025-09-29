import React, { useState } from 'react';

const AddStudent = () => {
  const [student, setStudent] = useState({ name: '', standard: '' });

  const handleChange = (e) => {
    setStudent({ ...student, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/students", {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(student)
      });

      if (response.ok) {
        alert("Student added successfully!");
        setStudent({ name: '', standard: '' });
      } else {
        alert("Failed to add student.");
      }
    } catch (error) {
      console.error("Error:", error);
      alert("Error adding student.");
    }
  };

return (
  <div>
    <h2>Add Student</h2>
    <form onSubmit={handleSubmit}>
      <div>
        <label>Name: </label>
        <input
          type="text"
          name="studentName"
          value={student.studentName}   // ✅ FIXED
          onChange={handleChange}
          required
        />
      </div>
      <div>
        <label>Standard: </label>
        <input
          type="text"
          name="standard"
          value={student.standard}
          onChange={handleChange}
          required
        />
      </div>
      <button type="submit">Add Student</button>
    </form>
  </div>
);
};

export default AddStudent;