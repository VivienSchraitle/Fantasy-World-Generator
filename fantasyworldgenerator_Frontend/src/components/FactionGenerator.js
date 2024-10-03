import React, { useState, useEffect } from 'react';
import axios from 'axios';

const FactionGenerator = () => {
  const [factionDetails, setFactionDetails] = useState("");
  const [inputs, setInputs] = useState({
    scale: "50",
    funds: "50",
    magic: "50",
    military: "50",
    religion: "50",
    reputation: "50",
    intensity: "3",
    primPercent: "60",
    secPercent: "30",
    primaryHeritage: "",
    secondaryHeritage: "",
  });

  const [heritages, setHeritages] = useState([]); // State to store heritage options

  // Fetch heritages from the backend
  useEffect(() => {
    const fetchHeritages = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/heritages");
        setHeritages(response.data); // Store the fetched heritages
      } catch (error) {
        console.error("Error fetching heritages:", error);
      }
    };

    fetchHeritages();
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setInputs((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const generateFaction = async () => {
    try {
      const requestData = {
        scale: inputs.scale,
        funds: inputs.funds,
        magic: inputs.magic,
        military: inputs.military,
        religion: inputs.religion,
        reputation: inputs.reputation,
        intensity: inputs.intensity,
        primPercent: inputs.primPercent,
        secPercent: inputs.secPercent,
        primaryHeritage: inputs.primaryHeritage,
        secondaryHeritage: inputs.secondaryHeritage,
      };

      const response = await axios.post("http://localhost:8080/api/faction/generate", requestData);

      if (response.status >= 200 && response.status < 300) {
        setFactionDetails(response.data);
      } else {
        setFactionDetails(`Unexpected response with status code: ${response.status}`);
      }
    } catch (error) {
      console.error("Error generating faction:", error);
      setFactionDetails("Error generating faction.");
    }
  };

  return (
    <div>
      <h1>Faction Generator</h1>

      {/* Input form */}
      <div>
        <label>Scale: <input type="text" name="scale" value={inputs.scale} onChange={handleInputChange} /></label>
        <label>Funds: <input type="text" name="funds" value={inputs.funds} onChange={handleInputChange} /></label>
        <label>Magic: <input type="text" name="magic" value={inputs.magic} onChange={handleInputChange} /></label>
        <label>Military: <input type="text" name="military" value={inputs.military} onChange={handleInputChange} /></label>
        <label>Religion: <input type="text" name="religion" value={inputs.religion} onChange={handleInputChange} /></label>
        <label>Reputation: <input type="text" name="reputation" value={inputs.reputation} onChange={handleInputChange} /></label>
        <label>Intensity: <input type="text" name="intensity" value={inputs.intensity} onChange={handleInputChange} /></label>
        <label>PrimPercent: <input type="text" name="primPercent" value={inputs.primPercent} onChange={handleInputChange} /></label>
        <label>SecPercent: <input type="text" name="secPercent" value={inputs.secPercent} onChange={handleInputChange} /></label>

        {/* Primary Heritage dropdown */}
        <label>
          Primary Heritage:
          <select name="primaryHeritage" value={inputs.primaryHeritage} onChange={handleInputChange}>
            <option value="">Select Primary Heritage</option>
            {heritages.map((heritage) => (
              <option key={heritage.name} value={heritage.name}>
                {heritage.name}
              </option>
            ))}
          </select>
        </label>

        {/* Secondary Heritage dropdown */}
        <label>
          Secondary Heritage:
          <select name="secondaryHeritage" value={inputs.secondaryHeritage} onChange={handleInputChange}>
            <option value="">Select Secondary Heritage</option>
            {heritages.map((heritage) => (
              <option key={heritage.name} value={heritage.name}>
                {heritage.name}
              </option>
            ))}
          </select>
        </label>
      </div>

      {/* Button to generate faction */}
      <button onClick={generateFaction}>Generate Faction</button>

      {/* Display faction details */}
      <div>
        <h2>Faction Details:</h2>
        <pre>{factionDetails}</pre>
      </div>
    </div>
  );
};

export default FactionGenerator;
