# Readme
Line width is set to 160 chars.

## App architecture
The App arhitecture is MVVM, using clear separations of layers allowing testing of created logic.

## LiveData and LiveEvent
LiveData is used to allow correct observations and lifecycle handling by Views.
Note that LiveEvent is a custom modification of LiveData to allow events being fired and observed correctly.

## ViewModels
ViewModels, together with LiveData, are used to allow Views (Activities and Fragment) to observe data emitted.
Logic in ViewModels is tested with Unit Tests.

## Repos
Repositories are used to be responsible for data retrieval and modifying, interacting mostly directly with ViewModels.
This allows data sources to be anywhere, including local or remote, without the ViewModel needing to know where the data is coming from.

## Room and DB
Room is used at the local database, where all data is stored before being emitted to observers.
This is done so that retrieved data is always stored locally before being used in order to always have some data to show user while new data is being retrieved.

## Dependency Injection
Dagger2 is used as the Dependency Injection framework to allow decoupling of dependencies, and the platform built in AndroidInjection functionality
is used to further decouple dependencies.

## UI
UI is very simple and would require actual UI/UX to get right.
AdapterDelegatesManager is used to allow composition of list items, allowing an unlimited number of list items types.

## Possible improvements
- Better UI/UX.
- More User control over time/dates for retrieved data.
- "More Details" fragment for forecast details of specific using new Navigation Architecture.