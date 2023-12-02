local utils = require("utils")

local max_cube_amounts = {
	red = 12,
	green = 13,
	blue = 14,
}
local id_counts = 0

local lines = utils.read_lines("CubeConundrum.txt")
if lines == nil then
	return
end

for id, line in ipairs(lines) do
	local sets_str = utils.split(line, ":")[2]
	local sets = utils.split(sets_str, ";")
	local is_impossible = false

	for _, set in ipairs(sets) do
		local cubes = utils.split(set, ",")

		for _, cube in ipairs(cubes) do
			-- data[1] amount of the color data[2] color name
			local data = utils.split(cube, " ")

			if max_cube_amounts[data[2]] < tonumber(data[1]) then
				is_impossible = true
			end
		end
	end

	if is_impossible == false then
		id_counts = id_counts + id
	end
end

print(id_counts)
