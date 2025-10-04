# Odin User Guide

![Screenshot](/docs/Odin_Preview.png)

Odin is a simple task management chatbot that helps users organize and
track their tasks efficiently. It supports adding, marking, unmarking,
deleting, and searching tasks, including todos, deadlines, and events.

Task data is stored persistently and automatically saved after each command, so no work is lost.

## Adding todos: `todo`

Adds a basic task.

Format:

```
todo TASK_DESCRIPTION
```

Example:

```
todo homework
```

Expected Output:

```
Successfully added:
[T][ ] homework
```

## Adding deadlines: `deadline`

Adds a task with a deadline.

Format:

```
deadline TASK_DESCRIPTION /by YYYY-MM-DD
```

Example:

```
deadline EE2026 Assignment /by 2025-10-06
```

Expected Output:

```
Successfully added:
[D][ ] EE2026 Assignment (by Oct 06 2025)
```

## Adding events: `event`

Adds a task with a start time and end time.

Format:

```
event TASK_DESCRIPTION /from YYYY-MM-DD /to YYYY-MM-DD
```

Example:

```
event Orientation /from 2025-08-08 /to 2025-08-10
```

Expected Output:

```
Successfully added:
[E][ ] Orientation (from: Aug 08 2025 to: Aug 10 2025)
```

## Listing tasks: `list`

Lists all the tasks currently.

Format:

```
list
```

Example:

```
list
```

Expected Output:

```
These are your tasks.
1. [T][ ] homework
2. [D][ ] EE2026 Assignment (by Oct 06 2025)
3. [E][ ] Orientation (from: Aug 08 2025 to: Aug 10 2025)
```

## Marking a task: `mark`

Marks a task as done.

Format:

```
mark TASK_NUMBER
```

Example:

```
mark 2
```

Expected Output:

```
Nice! Another task down!
   [D][X] EE2026 Assignment (by Oct 06 2025)
```

## Unmarking a task: `unmark`

Marks a task as not done.

Format:

```
unmark TASK_NUMBER
```

Example:

```
unmark 2
```

Expected Output:

```
Task unmarked. Please tell me it was a misinput.
   [D][ ] EE2026 Assignment (by Oct 06 2025)
```

## Deleting a task: `delete`

Deletes a task from the tasklist.

Format:

```
delete TASK_NUMBER
```

Example:

```
delete 3
```

Expected Output:

```
Deleting task 3...
   [E][ ] Orientation (from: Aug 08 2025 to: Aug 10 2025)
You now have 2 tasks.
```

## Finding a task: `find`

Finds tasks with the matching keyword. Matching is case-insensitive.

Format:

```
find QUERY
```

Example:

```
find lab
```

Expected Output:

```
Querying...
1 matching task(s) were found in your list.
1. [D][ ] EE2026 Lab Task (by Oct 06 2025)
```