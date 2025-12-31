## 1. Persona (Read Only)
  
You are an expert seasoned mobile principal engineer with great experience in building performant, efficient, robust crossplatform apps using the Kotlin programming language and Compose Multiplatform framework.
  

## 2. Project Requirement (Read Only)

### 2.1. Requirement Synopsis
I want to create a crossplatform (Android & iOS) mobile app using Compose Multiplatform, that helps in navigate the Mumbai transit lines, starting with the Mumbai Local Railway lines and then connecting them with the Mumbai Metro lines.

The app should be simple to use, performant, following latest industry standards and guidelines.

### 2.2. About Mumbai Transit Lines

1. There are mainly 2 railway divisions - Western Railway (WR) and Central Railway (CR)

2. The 2 divisions are split into 5 lines mainly - Western, Central, Harbour, Trans-harbour, Port.
- Western Line comes under WR and runs from Churchgate to Dahanu Road
- Central Line comes under CR and runs from CSMT to Khopoli and CSMT to Kasara
- Harbour Line comes under CR and runs from CSMT to Panvel, CSMT to Goregaon and one route from Panvel to Goregaon
- Trans-harbour Line comes under CR and runs from Thane to Panvel and Thane to Vashi
- Port Line comes under CR and runs from Uran to Nerul and Uran from Belapur CBD

3. There are sections of routes that overlaps with other routes

4. Interchange stations are the main hub stations where commuters can switch lines. The main hub stations are;
- CSMT, Dadar, Kurla, Vashi, Kalyan, Thane, Nerul, Belapur CBD, Andheri, Goregaon, Borivali, Virar, Dahanu Road.

5. It takes some time for the commuters to change the lines at these interchange stations. This is to be considered while planning routes from a source and destination.

### 2.3. Project Requirement Details

#### 2.3.1. Data Source

All the data required for this app will be stored in a pre-populated sqlite database that has 2 tables - "schedule" & "trains".

1.  "trains" table has the main columns - "id", "train_number", "train_code"
- "id" : unique ID of the train
- "train_number" : train number of the train
- "train_code" : train code of the train

2.  "schedule" table has the main columns - "id", "train_id", "station_name", "arrival_time", "stop_sequence"
- "id" : unique ID of the train stop sequence
- "train_id" : equals to the "id" of the train from the "trains" table. So "train_id" is the foreign key to "id" in "trains" table.
- "station_name" : station name of the stop
- "arrival_time" : arrival time of the stop
- "stop_sequence" : stop sequence of the stop

3. File ./artefacts/stations_master_list.json holds the master list of all the stations in the Mumbai local railway system.
It holds;
- Station name and it's alternate names
- Station code of each station
- Division : "CR" or "WR"
- Line : "western", "central", "harbour", "trans-harbour", "port"
- Distance from CSMT for CR and from Churchgate for WR

4. File ./artefacts/routes_master_list.json holds the various routes that runs on each line. 
This details out each of the stops and it's sequence. This helps in determining if a train is 'fast' train that skips stops in between or 'slow' train that stops at every station.

#### 2.3.2. Home Screen

1. Get the user current location and mark the default station to the station closest to the user's location

2. Show next 3 trains each, departing to the various terminals in all the routes from the default station

3. Show saved routes/plans

#### 2.3.3. Route Screen
Explore various routes from a source station to the terminal stations that are on the current line of the source station and get the train schedule of the various trains in that route. 
Save a source station to terminal station route for quick train schedule lookup in the route.

1. Enter the source station (defaults to station at current location if available)

2. Prompt the user to select any of the terminal stations on the railway lines the source station is a part of.
Example: Vashi is part of Harbour Line (with terminals at Panvel and CSMT), Trans-harbour Line (with terminals at Thane and Vashi), Panvel-Goregaon line (with terminals at Panvel and Goregaon).
So when the source is set as "Vashi", it should prompt to select terminal station between Panvel, CSMT, Goregaon.

3. List the train schedules that run on the selected route.

4. Option to save the route.

#### 2.3.4. Plan Screen
Get the route plan with train schedule and schedules of the interchange trains if required, from a source station to the destination station, considering time required to switch lines at interchange stations, train schedules.

1. Enter the source station (defaults to station at current location if available)

2. Enter the destination station

3. Plan the route in between considering the train schedules, interchange stations and time taken to switch lines.

4. Ability to add journey start time, and set filters like "prefer fast trains", "prefer ladies special", "exclude ladies special", "prefer AC"

5. Display the route as per the set conditions

6. Option to save the route plan.

#### 2.3.5. Future Scope

1. Alert commuter travelling in the train before the destination station arrives.

2. Metro transit lines and it's connection to the railway lines.

3. Get fare details for a journey from point A to B.

4. Platform information of the train at a station.

5. Book tickets.

## 3. Important Instructions (Read Only)
  
1. Use the web search tool to learn about the latest about a library/framework from the official docs.
     
2. Use chain-of-thoughts and trees-of-thought to improve the accuracy of your response.  
     
3. Do not assume anything which is not explicitly mentioned in the requirement. Immediately clarify by asking questions or feedbacks.  
     
4. Mark every response of yours with a unique identifier (example: "RES_001"), so that I can refer it to reply or ask follow-up questions and to callback.  
     
5. It is OK for you to say that you do not know something and we will figure about it together.  
  
6. IT IS VERY IMPORTANT THAT YOU ask me as many questions required, so that we have the best understanding between us. DO NOT ASSUME AND GENERATE CODE DIRECTLY.  



# Project Planning Document - TranSwift : Mumbai Transit App (Read Only)

## 1. Project Overview
A cross-platform (Android & iOS) mobile application built with Kotlin and Compose Multiplatform to facilitate navigation of Mumbai's transit system, specifically focusing on Railway lines initially, with future extensibility for Metro lines.

## 2. Technical Stack
- **Language:** Kotlin
- **Framework:** Compose Multiplatform (Android & iOS)
- **Architecture:** MVI (Model-View-Intent) with Clean Architecture (Data, Domain, UI layers)
- **Dependency Injection:** Koin
- **Navigation:** Jetbrains Compose Navigation
- **Persistence:** Room (KMP) with pre-populated SQLite database support
- **Asynchrony:** Kotlin Coroutines & Flows
- **UI Design System:** Material Design 3

## 3. Phased Development Plan

### Phase 1: Inception, Architecture & Data Strategy
**Goal:** Establish the project structure and secure the data foundation.
- [ ] **Project Setup:** Verify Compose Multiplatform configuration.
- [ ] **Data Modeling:** Define Entities `Train`, `Schedule`, `Station`, `Route`.
- [ ] **Database Setup:** Implement Room (KMP) to read from the provided `transwift.db` (pre-populated SQLite).
- [ ] **Data Ingestion:** Parse provided JSON artifacts:
    -   `stations_master_list.json`: Station details, codes, divisions.
    -   `routes_master_list.json`: Route definitions (Fast/Slow logic).
- [ ] **Architecture Skeleton:** Setup DI (Koin), ViewModels (MVI), and basic Navigation graph (Home, Routes, Plan).

### Phase 2: Core Logic & Routing Engine (The Brain)
**Goal:** Implement the business logic for scheduling and routing independently of UI.
- [ ] **Station Repository:** Manage station data from JSON/DB.
- [ ] **Schedule Engine:** Query DB for `getNextTrains(station, direction, count)`.
- [ ] **Routing Algorithm:** Implement offline on-device routing (A* or Dijkstra) for `findRoute(source, dest, time, filters)`.
    - Must account for: Interchange penalties (switching time), fast/slow tracks, ladies/AC filters.
- [ ] **Unit Testing:** Rigorous testing of the routing engine.

### Phase 3: UI - Route Explorer (Single Line)
**Goal:** "Explore various routes from a source station to the terminal stations."
- [ ] **Selection UI:** Source Station picker -> Context-aware Terminal picker.
- [ ] **Schedule List:** Display trains with filters.
- [ ] **Persistence:** Save favorite routes locally.

### Phase 4: UI - Journey Planner (Multi-Line)
**Goal:** "Plan the route... considering time required to switch lines."
- [ ] **Planner UI:** Source & Destination inputs, Time picker, Filters (AC, Ladies, Fast).
- [ ] **Results UI:** Detailed itinerary view (Walk -> Train A -> Transfer -> Train B).
- [ ] **Persistence:** Save journey plans.

### Phase 5: UI - Home Screen & Context
**Goal:** Smart dashboard (initially manual location, future: GPS).
- [ ] **Station Selector:** "You are at [Station Name]" (User manually selects default station, persisted).
- [ ] **Saved Searches:** Horizontal/Vertical list of saved Route/Plan cards.
- [ ] **Next Trains Towards:**
    -   Dynamic cards for each Terminal direction (e.g., "Towards Panvel").
    -   Each card lists top 3 upcoming trains with time & countdown.
- [ ] **Navigation:** Bottom Bar with [Home, Routes, Plan].

### Phase 6: Refinement & Future Scope
**Goal:** UX improvements, performance, and future features.
- [ ] **Geolocation:** Implement `findNearestStation(lat, long)` to auto-set home station.
- [ ] **UI Polish:** Animations, Transitions, Dark Mode support.
- [ ] **Performance:** Optimization of large list rendering and routing calculations.

## 4. Key Considerations & Assumptions
- **Data:** The app uses a provided `transwift.db` and JSON artifacts.
- **Routing:** All routing calculations are performed **offline** on the device.
- **Geolocation:** Deferred to Phase 6. Users must manually select their "Home/Current" station initially.
- **Interchange:** Hardcoded transfer times for major hubs (e.g., Dadar = 5 mins, CSMT = 3 mins).



***

# For Gemini's use

## Gemini Added Memories
- The user prefers a "consultation mode" to clarify requirements through Q&A before starting execution.
- User has provided `transwift.db` in `artefacts/` (verification pending) and requires MVI + Clean Architecture.
- User switched from "consultation mode" back to "code generation mode" for implementation.
  
## Changelog
- **[2025-12-29] Phase 1 Initialization:**
    - Updated `PRD.md` with MVI, Clean Arch, and Offline Routing requirements.
    - Updated `libs.versions.toml` and `composeApp/build.gradle.kts` with dependencies for Room, Koin, Navigation, and Serialization.
    - Resolved KSP plugin version conflict by updating to `2.3.4` and fixing repository access in `settings.gradle.kts`.
    - Build failed due to host machine Xcode configuration (`xcrun` error).

## Active Tasks 
- [ ] **Fix Host Environment:** User needs to resolve Xcode command line tools issue (`xcode-select --install`, license agreement).
- [ ] **Verify Artifacts:** Confirm `transwift.db` is actually present in `artefacts/`.
- [ ] **Project Build:** Run `./gradlew build` successfully after environment fix.
- [ ] **Database Implementation:** Define Room Entities (`Train`, `Schedule`) and setup Database connection to pre-populated file.

