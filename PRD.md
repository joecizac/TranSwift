# Project Planning Document - Mumbai Transit App (TranSwift)

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

## 5. Areas for Clarification (To Be Resolved)
- **Artifacts:** I assume the `./artefacts/` folder with `transwift.db` and JSON files will be made available in the project root before Phase 1 coding begins.
