import React, { useState, useEffect } from 'react';
import api from '../services/api';
import '../styles/index.css';

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

  const [heritages, setHeritages] = useState([]);

  const fieldConfig = {
    facName: { label: "Faction Name", editable: true },
    powerType: { label: "Power Type", editable: true },
    votingSystem: { label: "Voting System", editable: true },
    goalsArray: { label: "Goals", editable: true },
    domainsArray: { label: "Domains", editable: true },
    leadership: { label: "Leadership", editable: true },
    joinRitual: { label: "Join Ritual", editable: true },
    factionValues: { label: "Faction Values", editable: true },
    moneySources: { label: "Money Sources", editable: true },
    doctrines: { label: "Doctrines", editable: true },
    data: { label: "Data" }, // Data label for rendering
  };

  useEffect(() => {
    const fetchHeritages = async () => {
      try {
        const response = await api.get("/api/heritages");
        setHeritages(response.data);
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

  const generateNewField = async (fieldName) => {
    try {
      const requestData = { fieldName };
      const response = await api.post("/api/faction/generateField", requestData);

      if (response.status >= 200 && response.status < 300) {
        setFactionDetails((prevDetails) => ({
          ...prevDetails,
          [fieldName]: response.data[fieldName],
        }));
      } else {
        console.error(`Error generating new ${fieldName}: ${response.status}`);
      }
    } catch (error) {
      console.error(`Error generating new ${fieldName}:`, error);
    }
  };

  const renderFactionDetails = () => {
    if (!factionDetails) return <p>No faction details available.</p>;

    const categoryOrder = ["Insane", "High", "Mid", "Low"];

    return (
      <div className="faction-details-container">
        {Object.entries(factionDetails).map(([key, value]) => {
          const field = fieldConfig[key];
          if (!field) return null;

          // Handle the "moneySources" field specially
          if (key === 'moneySources' && typeof value === 'object') {
            const sortedMoneySources = Object.entries(value).sort(
              ([sourceA], [sourceB]) => {
                const indexA = categoryOrder.indexOf(sourceA);
                const indexB = categoryOrder.indexOf(sourceB);
                return indexA - indexB;
              }
            );

            return (
              <div key={key} className="faction-detail">
                <label className="detail-label">{field.label}:</label>
                <div className="money-sources">
                  {sortedMoneySources.map(([source, methods]) => (
                    <div key={source}>
                      <strong>{source}:</strong>
                      <ul>
                        {methods.map((method, index) => (
                          <li key={index}>{method}</li>
                        ))}
                      </ul>
                    </div>
                  ))}
                </div>
                {field.editable && (
                  <button className="factionButton" onClick={() => generateNewField(key)}>
                    Generate New {field.label}
                  </button>
                )}
              </div>
            );
          }

          // Handle the "data" field specially
          if (key === 'data' && typeof value === 'object') {
            return (
              <div key={key} className="faction-detail">
                <label className="detail-label">Data:</label>
                <div className="faction-data">
                  {Object.entries(value).map(([dataKey, dataValue]) => (
                    <p key={dataKey}>
                      {dataKey.replace(/([A-Z])/g, ' $1')}: {dataValue}
                    </p>
                  ))}
                </div>
              </div>
            );
          }

          // Default rendering for other fields
          return (
            <div key={key} className="faction-detail">
              <label className="detail-label">{field.label}:</label>
              <span>{value}</span>
              {field.editable && (
                <button className="factionButton" onClick={() => generateNewField(key)}>
                  Generate New {field.label}
                </button>
              )}
            </div>
          );
        })}
      </div>
    );
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

      const response = await api.post("/api/faction/generate", requestData);

      if (response.status >= 200 && response.status < 300) {
        setFactionDetails({
          ...response.data,
          data: { // Add the data object here from inputs
            sizeScale: inputs.scale,
            magicScore: inputs.magic,
            intensityScore: inputs.intensity,
            militaristicScore: inputs.military,
            financeScore: inputs.funds,
            reputationScore: inputs.reputation,
            religionScore: inputs.religion,
            primPercent: inputs.primPercent,
            secPercent: inputs.secPercent,
            primHeritageName: inputs.primaryHeritage,
            secHeritageName: inputs.secondaryHeritage,
          },
        });
      } else {
        console.error(`Unexpected response: ${response.status}`);
      }
    } catch (error) {
      console.error("Error generating faction:", error);
    }
  };

  return (
    <div className="faction-generator-container">
      <h1>Faction Generator</h1>

      {/* Input form for generating new faction */}
      <div className="input-section">
        <h2>Generate a Faction</h2>
        <label>Scale: <input type="text" name="scale" value={inputs.scale} onChange={handleInputChange} /></label>
        <label>Funds: <input type="text" name="funds" value={inputs.funds} onChange={handleInputChange} /></label>
        <label>Magic: <input type="text" name="magic" value={inputs.magic} onChange={handleInputChange} /></label>
        <label>Military: <input type="text" name="military" value={inputs.military} onChange={handleInputChange} /></label>
        <label>Religion: <input type="text" name="religion" value={inputs.religion} onChange={handleInputChange} /></label>
        <label>Reputation: <input type="text" name="reputation" value={inputs.reputation} onChange={handleInputChange} /></label>
        <label>Intensity: <input type="text" name="intensity" value={inputs.intensity} onChange={handleInputChange} /></label>
        <label>Primary Heritage:
          <select name="primaryHeritage" value={inputs.primaryHeritage} onChange={handleInputChange}>
            <option value="">Select Primary Heritage</option>
            {heritages.map((heritage) => (
              <option key={heritage.name} value={heritage.name}>{heritage.name}</option>
            ))}
          </select>
        </label>
        <label>Secondary Heritage:
          <select name="secondaryHeritage" value={inputs.secondaryHeritage} onChange={handleInputChange}>
            <option value="">Select Secondary Heritage</option>
            {heritages.map((heritage) => (
              <option key={heritage.name} value={heritage.name}>{heritage.name}</option>
            ))}
          </select>
        </label>
        <button className="factionButton" onClick={generateFaction}>Generate Faction</button>
      </div>

      {/* Display faction details */}
      <div className="faction-details-section">
        <h2>Faction Details</h2>
        {renderFactionDetails()}
      </div>
    </div>
  );
};

export default FactionGenerator;
