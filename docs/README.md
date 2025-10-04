# Odin User Guide

![Screenshot](/docs/Odin_Preview.png)

Odin is a simple task management chatbot that helps users organize and
track their tasks efficiently. It supports adding, marking, unmarking,
deleting, and searching tasks, including todos, deadlines, and events.

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