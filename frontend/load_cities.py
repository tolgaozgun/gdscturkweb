import json
import mysql.connector

# Connect to the MySQL database
cnx = mysql.connector.connect(
    host='localhost',
    user='gdscturk',
    password='gdscturk',
    database='gdscturk'
)
cursor = cnx.cursor()

# Read JSON file
with open('cities.json') as json_file:
    data = json.load(json_file)

# Iterate over JSON data and insert values into the table
for entry in data:
    lat = entry['lat']
    lon = entry['lon']
    city_id = entry['plaka']
    name = entry['label']
    country_id = 1  # Assuming country_id is always 1

    # Insert values into the table
    query = "INSERT INTO cities (latitude, longitude, city_id, country_id, name) VALUES (%s, %s, %s, %s, %s)"
    values = (lat, lon, city_id, country_id, name)
    cursor.execute(query, values)
    cnx.commit()

# Close the database connection
cursor.close()
cnx.close()
