local M = {}

M.read_lines = function(path)
	local file = io.open(path)

	if file == nil then
		return error("File cannot be found. Make sure you ran the script from the correct location.")
	end

	local lines = {}
	for line in file:lines() do
		table.insert(lines, line)
	end

	return lines
end

M.print_table = function(table)
	for _, item in ipairs(table) do
		print(item)
	end
end

M.split = function(input, separator, trim)
	separator = separator or "%s"

	local results = {}
	for str in string.gmatch(input, "([^" .. separator .. "]+)") do
		if trim == nil or trim == true then
			table.insert(results, M.trim(str))
		end
		if trim == false then
			table.insert(results, str)
		end
	end
	return results
end

M.trim = function(str)
	return (str:gsub("^%s*(.-)%s*$", "%1"))
end

return M
