import React, { useEffect, useState } from 'react';

const ViewStudent = () => {
  const [students, setStudents] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/students")
      .then(response => response.json())
      .then(data => setStudents(data))
      .catch(error => console.error("Error fetching students:", error));
  }, []);

  return (
    <div>
      <h2>View Students</h2>
      <table border="1" cellPadding="10">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Standard</th>
          </tr>
        </thead>
        <tbody>
          {students.length > 0 ? (
            students.map(s => (
              <tr key={s.id}>
                <td>{s.id}</td>
                <td>{s.studentName}</td>
                <td>{s.standard}</td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="3">No students found</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ViewStudent;
