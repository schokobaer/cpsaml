# ITS Cockpit

## Viewpoints
The cockpit appliction is made up from different monitors. Those monitors can be configured 
in the Admin viewpoint and are used in  the Monitor viewpoint. To switch the viewpoint click on the
symbol in the right top corner.

### Admin
In the Admin viewpoint you see a dropdown with all available monitors. To add a dashboard click on the `+`
symbol. To remove the currently selected one click on the `-` symbol. If you have selected a dashboard
you can see four sections for the dashboard: The first three are monitor sections and the last one is
the action section. So in a dashboard you can specify monitors to observe and you can specify actions
to execute.

#### Adding a new Monitor
When adding a viewpoint you have
to set a name and select the type of CPS. A dashboard can always dashboard only one specific type of CPS.
The Systemwide Digital Twin also counts as a CPS in the whole application. Further you can restrict the
dashboard to only dashboard one specific instance of the selected CPS type. For this you have to know the ID
of this instance in the textbox. In the dropdown beside you can see all currently available instances of the
selected CPS type. If you select one it will be transfered to the textbox.

#### Property Objective
A `PropertyObjective` just displays the value of a property of the CPS.

##### Source
You can either select the `Path` to
the property to display or you can select one of the customs, which will be described later. When using the
`Path` you have to make sure that the final propeh4rty you select is a primitive or a list of primitives. If you
select a list, you can either use a `ScalarFunction`, which maps the list items to a single item or you choose
to use the whole list, which then shows you all items in the list.

##### Representation
You can choose between three representations. The default representation is `Text`, which just displays the value
in text format. A more advanced representation is the `Graph` representation, which displays the values in a chart line
over the last 20 seconds. The prerequisite to use a `Graph`
representation is that the selected source is of type `INT`, `FLOAT` or you used the `Count` `ScalarFunction` on
a list. The most advanced representation is `Range`. It lets you display the value in a bar. The prerequisites for
this is that the type of the selected source is of type `INT` or `FLOAT` and `Bounds` are specified for the source.
So there is an order of the available representations like the following: `Text` < `Graph` < `Range`, which means the
higher the order the more prerequisites are needed to apply the representation.

##### Severities
Severities can be specified optionally for a `PropertyObjective`. A Severity maps the value of the property to a
`SeverityLevel`, which are `Green`, `Orange` and `Red`.





## TDOO:
  - benennung von monitors gut? literatur nachsuchen